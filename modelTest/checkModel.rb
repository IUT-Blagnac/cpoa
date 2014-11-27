# -*- coding: utf-8 -*-
# usage: ruby checkModel.rb [file] [mainClass] [interface]
# -------------------
# Author::    Jean-Michel Bruel  (mailto:jbruel@gmail.com) improved by JMI
# Copyright:: Copyright (c) 2014 JMB
# License::   Distributes under the same terms as Ruby
# -------------------

require "minitest/autorun"
MODEL_NAME = ARGV[0] ? ARGV[0] : "model.uml"
MAIN_CLASS = ARGV[1] ? ARGV[1] : "Personnage"
INTERFACE = ARGV[2] ? ARGV[2] : "ComportementArme"

module MiniTest
  class Unit
    class TestCase
      #Define new assertion
      def assert_contains(string_to_test, substring_to_verify)
        assert_match( string_to_test, substring_to_verify)
      end
      def assert_not_contains(string_to_test, substring_to_verify)
        assert_not_match( string_to_test, substring_to_verify)
      end
    end
  end
end
MiniTest::Unit.after_tests { p @_assertions }

class TestGeneratedModel < MiniTest::Unit::TestCase
  #------------ General tests about plantUML
  def test_generated_model_exists
    print assert_equal(true, File.exists?(MODEL_NAME))
  end

  def test_generated_model_is_plantuml
    assert_equal(true, File.readlines(MODEL_NAME).grep(/@startuml/).any?)
    assert_equal(true, File.readlines(MODEL_NAME).grep(/@enduml/).any?)
  end

  def test_generated_model_exists
    assert_equal(true, File.exists?(MODEL_NAME))
  end

  #------------ Specific tests about STRATEGY implementation for MAIN_CLASS through the INTERFACE behavior

  def test_class_MainClass_is_abstract
    assert_equal(true, File.readlines(MODEL_NAME).grep(/abstract #{MAIN_CLASS}/).any?)
  end

  def test_class_MAIN_CLASS_has_INTERFACE_behavior
    assert_contains(/#{MAIN_CLASS}\s+-->.*#{INTERFACE}/, File.readlines(MODEL_NAME).join)
  end

  def test_INTERFACE_is_an_Interface
    assert_equal(true, File.readlines(MODEL_NAME).grep(/interface\s+#{INTERFACE}/).any?)
  end

  def test_INTERFACE_Interface_has_at_least_one_concrete_implementation
    assert_equal(true, File.readlines(MODEL_NAME).grep(/#{INTERFACE}\s+<\|\.\./).any?)
  end

end
